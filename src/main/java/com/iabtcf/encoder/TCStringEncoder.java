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

import java.util.Base64;

class TCStringEncoder {

    public static String encode(TCString tcString) {
        switch (tcString.getVersion()) {
            case 1:
                TCStringV1 v1 = (TCStringV1) tcString;
                return stringFromVector(TCStringV1.toBitVector(v1));
            case 2:
                TCStringV2 v2 = (TCStringV2) tcString;

                final ByteBitVector coreTc = v2.getCoreBitVector();

                StringBuilder builder = new StringBuilder();
                builder.append(stringFromVector(coreTc));
                if (!v2.getRemainingVectors().isEmpty()) {
                    for (ByteBitVector remainingVector : v2.getRemainingVectors()) {
                        builder.append(".");
                        builder.append(stringFromVector(remainingVector));
                    }
                }
                return builder.toString();
            default:
                throw new UnsupportedOperationException("Version " + tcString.getVersion() + "is unsupported yet");
        }
    }

    static String stringFromVector(ByteBitVector byteBitVector) {
        final byte[] encoded = Base64.getUrlEncoder().encode(byteBitVector.getBytes());
        return new String(encoded);
    }

}
