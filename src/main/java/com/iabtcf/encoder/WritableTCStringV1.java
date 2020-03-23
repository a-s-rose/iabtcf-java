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
import com.iabtcf.FieldDefs;
import com.iabtcf.decoder.TCStringV1;
import com.iabtcf.utils.BitSetIntIterable;
import com.iabtcf.utils.IntIterable;

import java.time.Instant;

public class WritableTCStringV1 extends TCStringV1 implements WritableTCString {
    private final ByteBitVector bbv;

    private WritableTCStringV1(ByteBitVector bitVector) {
        super(bitVector);
        this.bbv = bitVector;
    }

    public static WritableTCStringV1 fromBitVector(ByteBitVector bitVector) {
        return new WritableTCStringV1(bitVector);
    }

    public static ByteBitVector toBitVector(WritableTCStringV1 tcString) {
        return new ByteBitVector(tcString.bbv.getBytes());
    }

    @Override
    public void setLastUpdated(Instant lastUpdated) {
        bbv.writeInstant(FieldDefs.CORE_LAST_UPDATED, lastUpdated);

    }

    @Override
    public void setPurposesConsent(IntIterable purposesConsent) {
        WritableTCStringV2.writeFromBitset((BitSetIntIterable) purposesConsent, FieldDefs.V1_PURPOSES_ALLOW);
    }

    @Override
    public void setVendorConsent(IntIterable vendorConsent) {
        writeVendorsV1((BitSetIntIterable) vendorConsent, bbv, FieldDefs.V1_VENDOR_MAX_VENDOR_ID, FieldDefs.V1_VENDOR_BITRANGE_FIELD);
    }

    private void writeVendorsV1(BitSetIntIterable bitSetIntIterable, ByteBitVector bbv, FieldDefs maxVendor, FieldDefs vendorField) {
        // TODO
    }
}
