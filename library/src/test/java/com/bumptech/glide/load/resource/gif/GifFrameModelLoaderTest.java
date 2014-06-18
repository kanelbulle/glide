package com.bumptech.glide.load.resource.gif;

import com.bumptech.glide.Priority;
import com.bumptech.glide.gifdecoder.GifDecoder;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GifFrameModelLoaderTest {
    private GifFrameModelLoader loader;
    private GifDecoder decoder;

    @Before
    public void setUp() {
        loader = new GifFrameModelLoader();
        decoder = mock(GifDecoder.class);
    }

    @Test
    public void testIdIncludesGifDecoderIdAndFrameIndex() {
        String id = "asdfasd";
        int frameIndex = 124;
        when(decoder.getId()).thenReturn(id);
        when(decoder.getCurrentFrameIndex()).thenReturn(frameIndex);

        String loaderId = loader.getId(decoder);

        assertTrue(loaderId.contains(id));
        assertTrue(loaderId.contains(String.valueOf(frameIndex)));
    }

    @Test
    public void testAlwaysReturnsGivenDecoderFromFetcher() throws Exception {
        assertEquals(decoder, loader.getResourceFetcher(decoder, 100, 100).loadData(Priority.NORMAL));
    }
}