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

import java.util.Base64;

import com.iabtcf.BitReader;
import com.iabtcf.FieldDefs;
import com.iabtcf.exceptions.ByteParseException;
import com.iabtcf.exceptions.UnsupportedVersionException;

class TCStringDecoder {
    static BitReader vectorFromString(String base64UrlEncodedString) {
        // SegmentInputStream sis = new SegmentInputStream(base64UrlEncodedString, 0);
        // InputStream is = DECODER.wrap(sis);
        //
        //
        byte[] bytes = Base64.getUrlDecoder().decode(base64UrlEncodedString);
        return new BitReader(bytes);
    }

    /**
     * @throws ByteParseException if version field failed to parse
     * @throws UnsupportedVersionException invalid version field
     * @throws IllegalArgumentException if consentString is not in valid Base64 scheme
     */
    public static TCString decode(String consentString)
            throws IllegalArgumentException, ByteParseException, UnsupportedVersionException {
        String[] split = consentString.split("\\.");
        String base64UrlEncodedString = split[0];
        BitReader bitVector = vectorFromString(base64UrlEncodedString);

        int version = bitVector.readBits6(FieldDefs.CORE_VERSION);

        switch (version) {
            case 1:
                return TCStringV1.fromBitVector(bitVector);
            case 2:
                if (split.length > 1) {
                    BitReader[] remaining = new BitReader[split.length - 1];
                    for (int i = 1; i < split.length; i++) {
                        remaining[i - 1] = vectorFromString(split[i]);
                    }
                    return TCStringV2.fromBitVector(bitVector, remaining);
                } else {
                    return TCStringV2.fromBitVector(bitVector);
                }
            default:
                throw new UnsupportedVersionException("Version " + version + "is unsupported yet");
        }
    }
}
