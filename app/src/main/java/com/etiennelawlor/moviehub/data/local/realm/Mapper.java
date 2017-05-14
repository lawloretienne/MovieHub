package com.etiennelawlor.moviehub.data.local.realm;

/**
 * Created by etiennelawlor on 5/14/17.
 */

public interface Mapper<F, T> {
    T map(F data);
}
