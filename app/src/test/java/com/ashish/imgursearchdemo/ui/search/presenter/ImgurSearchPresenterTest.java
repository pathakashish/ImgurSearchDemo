package com.ashish.imgursearchdemo.ui.search.presenter;

import com.ashish.imgursearchdemo.R;
import com.ashish.imgursearchdemo.model.Image;
import com.ashish.imgursearchdemo.model.source.remote.ImgurRemoteSource;
import com.ashish.imgursearchdemo.ui.search.view.ImgurSearchView;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ImgurSearchPresenterTest {


    private ImgurRemoteSource dataSource = mock(ImgurRemoteSource.class);
    private ImgurSearchPresenter presenter = new ImgurSearchPresenter(dataSource);

    @Test
    public void testThatBindAssignsViewToPresenter() {
        assertNull(presenter.getView$app_debug());
        presenter.bind(mock(ImgurSearchView.class));
        assertNotNull(presenter.getView$app_debug());
    }

    @Test
    public void whenSearchReturnsResultsUpdateRecyclerViewIsCalled() {
        List<Image> images = new ArrayList();
        images.add(new Image("1", "asdas", "image/png", false, "https://i.imgur.com/MSeUg1x.png"));
        when(dataSource.getImages(anyString())).thenReturn(Single.just(images));
        ImgurSearchView view = mock(ImgurSearchView.class);
        presenter.bind(view);
        presenter.search("test");
        verify(view, times(1)).showContentsView();
        verify(view, times(1)).updateRecyclerView(anyList());
    }

    @Test
    public void whenApiReturnsEmptyResultsShowNoResultsAvailable() {
        when(dataSource.getImages(anyString())).thenReturn(Single.just(new ArrayList<>()));
        ImgurSearchView view = mock(ImgurSearchView.class);
        presenter.bind(view);
        presenter.search("test");
        verify(view, times(1)).showMessageWithSearchViewVisible(R.string.no_results_available);
    }

    @Test
    public void whenApiReturnsErrorShowErrorInLoading() {
        when(dataSource.getImages(anyString())).thenReturn(Single.error(new Throwable()));
        ImgurSearchView view = mock(ImgurSearchView.class);
        presenter.bind(view);
        presenter.search("test");
        verify(view, times(1)).showMessageWithSearchViewVisible(R.string.error_loading);
    }

    @Test
    public void whenSearchReturnsEmptyResultsShowEmptyListViewIsShown() {
        when(dataSource.getImages(anyString())).thenReturn(Single.just(new ArrayList<>()));
        ImgurSearchView view = mock(ImgurSearchView.class);
        presenter.bind(view);
        presenter.search("test");
        verify(view, times(1)).showMessageWithSearchViewVisible(R.string.no_results_available);
    }

    @Test
    public void whenNetworkIsAvailableShowContentsViewIsCalled() {
        ImgurSearchView view = mock(ImgurSearchView.class);
        presenter.bind(view);
        presenter.onNetworkAvailable();
        verify(view, times(1)).showContentsView();
    }

    @Test
    public void whenNetworkIsLostErrorViewIsShownWithNetworkNotAvailableMessage() {
        ImgurSearchView view = mock(ImgurSearchView.class);
        presenter.bind(view);
        presenter.onNetworkLost();
        verify(view, times(1)).showErrorView(R.string.network_not_available);
    }

    @Test
    public void testThatUnbindUnassignsViewToPresenter() {
        presenter.bind(mock(ImgurSearchView.class));
        presenter.unbind();
        assertNull(presenter.getView$app_debug());
    }
}
