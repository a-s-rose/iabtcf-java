package com.iabtcf.encoder;

/*-
 * #%L
 * IAB TCF Core Library
 * %%
 * Copyright (C) 2020 IAB Technology Laboratory, Inc
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.iabtcf.ByteBitVector;
import com.iabtcf.decoder.TCModelDecoder;
import com.iabtcf.model.TCModel;
import com.iabtcf.v1.BitVectorTCModelV1;
import com.iabtcf.v2.BitVectorTCModelV2;
import org.junit.Test;

import java.util.Base64;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TCModelEncoderImplTest {

    // FIXME: create TCModel without using Decoder!

    @Test
    public void testEncodeModelV1() {
        final TCModel tcModel = BitVectorTCModelV1.fromBitVector(getByteVector("BObdrPUOevsguAfDqFENCNAAAAAmeAAA"));

        String result = TCModelEncoder.instance().encode(tcModel);
        assertThat(result, is("BObdrPUOevsguAfDqFENCNAAAAAmeAAA"));
    }

    @Test
    public void testEncodeModelV2() {
        String core = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        final TCModel tcModel = BitVectorTCModelV2.fromBitVector(getByteVector(core));

        String result = TCModelEncoder.instance().encode(tcModel);
        assertThat(result, is(core));
    }

    @Test
    public void testCanEncodeFromModelWithRemainingParts() {
        String core = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        String remaining = "IFoEUQQgAIQwgIwQABAEAAAAOIAACAIAAAAQAIAgEAACEAAAAAgAQBAAAAAAAGBAAgAAAAAAAFAAECAAAgAAQARAEQAAAAAJAAIAAgAAAYQEAAAQmAgBC3ZAYzUw";
        String tcString = core + "." + remaining;

        final TCModel tcModel = BitVectorTCModelV2.fromBitVector(getByteVector(core), getByteVector(remaining));

        String result = TCModelEncoder.instance().encode(tcModel);
        assertThat(result, is(tcString));
    }

    private ByteBitVector getByteVector(String str) {
        return new ByteBitVector(Base64.getUrlDecoder().decode(str));
    }

}
