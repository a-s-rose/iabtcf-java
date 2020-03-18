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
import com.iabtcf.model.TCModel;
import com.iabtcf.v1.BitVectorTCModelV1;
import com.iabtcf.v2.BitVectorTCModelV2;

import java.util.Base64;

public class TCModelEncoderImpl implements TCModelEncoder {
    @Override
    public String encode(TCModel tcModel) {
        switch (tcModel.version()) {
            case 1:
                return vectorToString(BitVectorTCModelV1.toBitVector((BitVectorTCModelV1) tcModel));
            case 2:
                BitVectorTCModelV2 tcModelV2 = (BitVectorTCModelV2) tcModel;
                final ByteBitVector coreTc = tcModelV2.getCoreBitVector();

                StringBuilder builder = new StringBuilder();
                builder.append(vectorToString(coreTc));
                if (!tcModelV2.getRemainingVectors().isEmpty()) {
                    for (ByteBitVector remainingVector : tcModelV2.getRemainingVectors()) {
                        builder.append(".");
                        builder.append(vectorToString(remainingVector));
                    }
                }
                return builder.toString();
            default:
                throw new UnsupportedOperationException("Version " + tcModel.version() + "is unsupported yet");
        }
    }

    private String vectorToString(ByteBitVector byteBitVector) {
        return Base64.getUrlEncoder().encodeToString(byteBitVector.getBytes());
    }
}
