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

import com.iabtcf.decoder.TCModelDecoder;
import com.iabtcf.model.TCModel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
