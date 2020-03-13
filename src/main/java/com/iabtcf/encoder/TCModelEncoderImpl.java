package com.iabtcf.encoder;

import com.iabtcf.ByteBitVector;
import com.iabtcf.model.TCModel;
import com.iabtcf.v1.BitVectorTCModelV1;
import com.iabtcf.v1.TCModelV1;
import com.iabtcf.v2.BitVectorTCModelV2;
import com.iabtcf.v2.TCModelV2;

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
