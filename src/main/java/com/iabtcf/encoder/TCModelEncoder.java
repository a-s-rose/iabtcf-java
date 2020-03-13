package com.iabtcf.encoder;

import com.iabtcf.decoder.TCModelDecoder;
import com.iabtcf.decoder.TCModelDecoderImpl;
import com.iabtcf.model.TCModel;

/**
 * A Thread-safe binary encoder.
 *
 * @author arose
 */
public interface TCModelEncoder {

    static TCModelEncoder instance() {
        return new TCModelEncoderImpl();
    }

    String encode(TCModel tcModel);
}
