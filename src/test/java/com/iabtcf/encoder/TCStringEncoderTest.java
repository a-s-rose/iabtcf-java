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
import com.iabtcf.decoder.TCString;
import com.iabtcf.decoder.TCStringV1;
import com.iabtcf.decoder.TCStringV2;
import org.junit.Test;

import java.util.Base64;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TCStringEncoderTest {

    @Test
    public void testCanEncodeFromCoreAndRemaining() {
        String core = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        String remainder = "IFoEUQQgAIQwgIwQABAEAAAAOIAACAIAAAAQAIAgEAACEAAAAAgAQBAAAAAAAGBAAgAAAAAAAFAAECAAAgAAQARAEQAAAAAJAAIAAgAAAYQEAAAQmAgBC3ZAYzUw";
        String tcString = core + "." + remainder;

        TCString v2 = TCStringV2.fromBitVector(getByteVector(core), getByteVector(remainder));

        assertThat(TCStringEncoder.encode(v2), is(tcString));
    }

    @Test
    public void testCanEncodeFromCore() {
        String tcString = "COtybn4PA_zT4KjACBENAPCIAEBAAECAAIAAAAAAAAAA";
        TCString v2 = TCStringV2.fromBitVector(getByteVector(tcString));

        assertThat(TCStringEncoder.encode(v2), is(tcString));
    }

    @Test
    public void testCanEncodeFromV1() {
        String tcString = "BObdrPUOevsguAfDqFENCNAAAAAmeAAA";
        TCString v1 = TCStringV1.fromBitVector(getByteVector(tcString));

        assertThat(TCStringEncoder.encode(v1), is(tcString));
    }

    private ByteBitVector getByteVector(String str) {
        return new ByteBitVector(Base64.getUrlDecoder().decode(str));
    }
}
