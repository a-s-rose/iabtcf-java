package com.iabtcf.encoder;

import com.iabtcf.decoder.TCModelDecoder;
import com.iabtcf.model.TCModel;
import com.iabtcf.v1.BitVectorTCModelV1;
import com.iabtcf.v2.BitVectorTCModelV2;
import org.junit.Test;

import java.util.Base64;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TCModelEncoderImplTest {

    // FIXME: create TCModel without using Decoder!

    @Test
    public void testEncodeModelV1() {
        String tcString = "BObdrPUOevsguAfDqFENCNAAAAAmeAAA";
        final TCModel tcModel = TCModelDecoder.instance().decode(tcString);

        String result = TCModelEncoder.instance().encode(tcModel);
        assertThat(result, is(tcString));
    }

    @Test
    public void testEncodeModelV2() {
        String tcString = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        final TCModel tcModel = TCModelDecoder.instance().decode(tcString);

        String result = TCModelEncoder.instance().encode(tcModel);
        assertThat(result, is(tcString));
    }

    @Test
    public void testCanEncodeFromModelWithRemainingParts() {
        String tcString =
                "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA.IFoEUQQgAIQwgIwQABAEAAAAOIAACAIAAAAQAIAgEAACEAAAAAgAQBAAAAAAAGBAAgAAAAAAAFAAECAAAgAAQARAEQAAAAAJAAIAAgAAAYQEAAAQmAgBC3ZAYzUw";
        final TCModel tcModel = TCModelDecoder.instance().decode(tcString);

        String result = TCModelEncoder.instance().encode(tcModel);
        assertThat(result, is(tcString));
    }

    @Test
    public void testCanEncodeFromModelOnlyCore() {
        String tcString = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        final TCModel tcModel = TCModelDecoder.instance().decode(tcString);

        String result = TCModelEncoder.instance().encode(tcModel);
        assertThat(result, is(tcString));
    }

}