package com.etiennelawlor.moviehub.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etiennelawlor.moviehub.R;
import com.etiennelawlor.moviehub.network.models.Configuration;
import com.etiennelawlor.moviehub.network.models.Images;
import com.etiennelawlor.moviehub.network.models.Movie;
import com.etiennelawlor.moviehub.prefs.MovieHubPrefs;
import com.etiennelawlor.moviehub.ui.DynamicHeightImageView;
import com.etiennelawlor.moviehub.utilities.AnimationUtility;
import com.etiennelawlor.moviehub.utilities.ColorUtility;
import com.etiennelawlor.moviehub.utilities.DateUtility;
import com.etiennelawlor.moviehub.utilities.DisplayUtility;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by etiennelawlor on 12/17/16.
 */

public class MoviesAdapter extends BaseAdapter<Movie> {

    // region Constants
    public static final String PATTERN = "yyyy-MM-dd";
    // endregion

    // region Member Variables
    private FooterViewHolder footerViewHolder;
    private int ivWidth;
    // endregion

    // region Constructors

    public MoviesAdapter(Context context) {
        int screenWidth = DisplayUtility.getScreenWidth(context);
        ivWidth = screenWidth/2;
    }

    // endregion

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);

        final MovieViewHolder holder = new MovieViewHolder(v);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = holder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(adapterPos, holder.itemView);
                    }
                }
            }
        });

        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_footer, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = ((StaggeredGridLayoutManager.LayoutParams) v.getLayoutParams());
        layoutParams.setFullSpan(true);
        v.setLayoutParams(layoutParams);

        final FooterViewHolder holder = new FooterViewHolder(v);
        holder.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onReloadClickListener != null){
                    onReloadClickListener.onReloadClick();
                }
            }
        });

        return holder;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final MovieViewHolder holder = (MovieViewHolder) viewHolder;

        final Movie movie = getItem(position);
        if (movie != null) {
            resetInfoBackgroundColor(holder.infoLinearLayout);
            resetTitleTextColor(holder.titleTextView);
            resetSubtitleTextColor(holder.subtitleTextView);

            setUpThumbnail(holder, movie);
            setUpTitle(holder.titleTextView, movie);
            setUpSubtitle(holder.subtitleTextView, movie);
        }
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;
    }

    @Override
    protected void displayLoadMoreFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
            footerViewHolder.loadingFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void displayErrorFooter() {
        if(footerViewHolder!= null){
            footerViewHolder.loadingFrameLayout.setVisibility(View.GONE);
            footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addFooter() {
        isFooterAdded = true;
        add(new Movie());
    }

    // region Helper Methods
    private void setUpThumbnail(final MovieViewHolder vh, final Movie movie){
        final DynamicHeightImageView iv = vh.thumbnailImageView;

        double heightRatio = 3.0D/2.0D;

        iv.setHeightRatio(heightRatio);

        String posterUrl = getPosterUrl(iv.getContext(), movie);
        if (!TextUtils.isEmpty(posterUrl)) {
            Picasso.with(iv.getContext())
                    .load(posterUrl)
                    .resize(ivWidth, (int)(heightRatio*ivWidth))
                    .centerCrop()
                    .into(iv, new Callback() {
                        @Override
                        public void onSuccess() {
                            if(movie.getPosterPalette() != null){
                                setUpInfoBackgroundColor(vh.infoLinearLayout, movie.getPosterPalette());
                                setUpTitleTextColor(vh.titleTextView, movie.getPosterPalette());
                                setUpSubtitleTextColor(vh.subtitleTextView, movie.getPosterPalette());
                            } else {
                                Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                    public void onGenerated(Palette palette) {
                                        movie.setPosterPalette(palette);

                                        setUpInfoBackgroundColor(vh.infoLinearLayout, palette);
                                        setUpTitleTextColor(vh.titleTextView, palette);
                                        setUpSubtitleTextColor(vh.subtitleTextView, palette);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });

        }
    }

    private String getPosterUrl(Context context, Movie movie){
        String posterUrl = "";
        Configuration configuration = MovieHubPrefs.getConfiguration(context);
        if(configuration != null) {
            Images images = configuration.getImages();
            if (images != null) {

                List<String> posterSizes = images.getPosterSizes();
                if (posterSizes != null && posterSizes.size() > 0) {
                    String posterSize;
                    if (posterSizes.size() > 1) {
                        posterSize = posterSizes.get(posterSizes.size() - 2);
                    } else {
                        posterSize = posterSizes.get(posterSizes.size() - 1);
                    }

                    String secureBaseUrl = images.getSecureBaseUrl();
                    String posterPath = movie.getPosterPath();

                    posterUrl = String.format("%s%s%s", secureBaseUrl, posterSize, posterPath);
                }
            }
        }
        return posterUrl;
    }

    private void resetInfoBackgroundColor(LinearLayout ll) {
        ll.setBackgroundColor(ContextCompat.getColor(ll.getContext(), R.color.grey_800));
    }

    private void setUpInfoBackgroundColor(LinearLayout ll, Palette palette) {
        Palette.Swatch swatch = ColorUtility.getMostPopulousSwatch(palette);
        if(swatch != null){
            int startColor = ContextCompat.getColor(ll.getContext(), R.color.grey_800);
            int endColor = swatch.getRgb();

            AnimationUtility.animateBackgroundColorChange(ll, startColor, endColor);
        }
    }

    private void setUpTitle(TextView tv, Movie movie){
        String title = movie.getTitle();
        if (!TextUtils.isEmpty(title)) {
            tv.setText(title);
        }
    }

    private void resetTitleTextColor(TextView tv) {
        tv.setTextColor(ContextCompat.getColor(tv.getContext(), R.color.primary_text_light));
    }

    private void setUpTitleTextColor(final TextView tv, Palette palette){
        Palette.Swatch swatch = ColorUtility.getMostPopulousSwatch(palette);
        if(swatch != null){
            int startColor = ContextCompat.getColor(tv.getContext(), R.color.primary_text_light);
            int endColor = swatch.getTitleTextColor();

            AnimationUtility.animateTextColorChange(tv, startColor, endColor);
        }
    }

    private void setUpSubtitle(TextView tv, Movie movie){
        String releaseDate = movie.getReleaseDate();
        if (!TextUtils.isEmpty(releaseDate)) {
            Calendar calendar = DateUtility.getCalendar(releaseDate, PATTERN);
            tv.setText(String.format("%d", calendar.get(Calendar.YEAR)));
        }
    }

    private void resetSubtitleTextColor(TextView tv) {
        tv.setTextColor(ContextCompat.getColor(tv.getContext(), R.color.secondary_text_light));
    }

    private void setUpSubtitleTextColor(final TextView tv, Palette palette){
        Palette.Swatch swatch = ColorUtility.getMostPopulousSwatch(palette);
        if(swatch != null){
            int startColor = ContextCompat.getColor(tv.getContext(), R.color.secondary_text_light);
            int endColor = swatch.getBodyTextColor();

            AnimationUtility.animateTextColorChange(tv, startColor, endColor);
        }
    }
    // endregion

    // region Inner Classes

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        // region Constructors
        public HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @BindView(R.id.thumbnail_iv)
        DynamicHeightImageView thumbnailImageView;
        @BindView(R.id.info_ll)
        LinearLayout infoLinearLayout;
        @BindView(R.id.title_tv)
        TextView titleTextView;
        @BindView(R.id.subtitle_tv)
        TextView subtitleTextView;
        // endregion

        // region Constructors
        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        // region Views
        @BindView(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @BindView(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @BindView(R.id.reload_btn)
        Button reloadButton;
        // endregion

        // region Constructors
        public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        // endregion
    }
    // endregion
}
