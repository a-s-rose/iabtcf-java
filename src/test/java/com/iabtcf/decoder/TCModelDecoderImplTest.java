package com.iabtcf.decoder;

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
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Base64;

import com.iabtcf.model.TCModel;
import com.iabtcf.v1.BitVectorTCModelV1;
import com.iabtcf.v2.BitVectorTCModelV2;
import org.junit.Test;

public class TCModelDecoderImplTest {

    @Test
    public void testCanCreateModelFromTwoPartsString() {
        String tcString =
                "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA.IFoEUQQgAIQwgIwQABAEAAAAOIAACAIAAAAQAIAgEAACEAAAAAgAQBAAAAAAAGBAAgAAAAAAAFAAECAAAgAAQARAEQAAAAAJAAIAAgAAAYQEAAAQmAgBC3ZAYzUw";
        assertNotNull(TCModelDecoder.instance().decode(tcString));
    }

    @Test
    public void testCanCreateModelOnePartString() {
        String tcString = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        assertNotNull(TCModelDecoder.instance().decode(tcString));
    }

    @Test
    public void testCanCreateModelV2() {
        String tcString = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        final TCModel tcModel = TCModelDecoder.instance().decode(tcString);
        assertThat(tcModel.version(), is(2));
        assertThat(tcModel, instanceOf(BitVectorTCModelV2.class));
    }

    @Test
    public void testCanCreateModelV1() {
        String tcString = "BObdrPUOevsguAfDqFENCNAAAAAmeAAA";
        final TCModel tcModel = TCModelDecoder.instance().decode(tcString);
        assertThat(tcModel.version(), is(1));
        assertThat(tcModel, instanceOf(BitVectorTCModelV1.class));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldFailIfANonSupportedVersionIsPassed() {
        String tcString = Base64.getUrlEncoder().encodeToString(new byte[] { 13 });
        TCModelDecoder.instance().decode(tcString);
    }
}
