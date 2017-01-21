package com.proxerme.library.connection.list.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.list.ListRequest;
import com.proxerme.library.connection.list.entity.ProjectListEntry;
import com.proxerme.library.connection.list.result.ProjectListResult;
import com.proxerme.library.parameters.IndustryTypeParameter.IndustryType;
import com.proxerme.library.parameters.ShowHentaiParameter.ShowHentai;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for the projects of an {@link com.proxerme.library.connection.info.entity.Industry},
 * featuring options for types and other restrictions. (Usethe withParameter builders)
 * This API uses pagination.
 *
 * @author Ruben Gees
 */
public class IndustryProjectsRequest extends ListRequest<ProjectListEntry[]> {

    private static final String ENDPOINT = "industryprojects";

    private static final String ID_PARAMETER = "id";
    private static final String TYPE_PARAMETER = "type";
    private static final String SHOW_HENTAI_PARAMETER = "isH";
    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";

    private String id;
    @Nullable
    private String type;
    @Nullable
    private Integer showHentai;
    private int page;
    @Nullable
    private Integer limit;

    /**
     * The constructor.
     *
     * @param page The page to load from.
     */
    public IndustryProjectsRequest(@NonNull String id, @IntRange(from = 0) int page) {
        this.id = id;
        this.page = page;
    }

    /**
     * Sets the type of projects to load.
     *
     * @param type The type.
     * @return This Request.
     */
    public IndustryProjectsRequest withType(@Nullable @IndustryType String type) {
        this.type = type;

        return this;
    }

    /**
     * Sets if hentai should be included in the list.
     *
     * @param showHentai The option for hentai inclusion.
     * @return This Request.
     */
    public IndustryProjectsRequest withShowHentai(@Nullable @ShowHentai Integer showHentai) {
        this.showHentai = showHentai;

        return this;
    }

    /**
     * Sets the limit on how many items to load.
     *
     * @param limit The limit.
     * @return This Request.
     */
    public IndustryProjectsRequest withLimit(@IntRange(from = 1) @Nullable Integer limit) {
        this.limit = limit;

        return this;
    }

    @Override
    protected ProxerResult<ProjectListEntry[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ProjectListResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(ID_PARAMETER, id),
                new Pair<>(TYPE_PARAMETER, type),
                new Pair<>(SHOW_HENTAI_PARAMETER, showHentai),
                new Pair<>(PAGE_PARAMETER, page),
                new Pair<>(LIMIT_PARAMETER, limit)
        );
    }
}
