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
import com.iabtcf.decoder.TCStringV2;
import com.iabtcf.utils.BitSetIntIterable;
import com.iabtcf.utils.IntIterable;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

public class WritableTCStringV2 extends TCStringV2 implements WritableTCString {
    private final ByteBitVector bbv;
    private final Collection<ByteBitVector> remainingVectors;

    private WritableTCStringV2(ByteBitVector bbv) {
        this(bbv, new ByteBitVector[]{});
    }

    private WritableTCStringV2(ByteBitVector bbv, ByteBitVector... theRest) {
        super(bbv, theRest);
        this.bbv = bbv;
        this.remainingVectors = Arrays.asList(theRest);
    }

    public static WritableTCStringV2 fromBitVector(ByteBitVector coreBitVector, ByteBitVector... remainingVectors) {
        return new WritableTCStringV2(coreBitVector, remainingVectors);
    }

    static void writeFromBitset(BitSetIntIterable bitSetIntIterable, FieldDefs fieldDefs) {
        //TODO
    }

    public ByteBitVector getCoreBitVector() {
        return bbv;
    }

    public Collection<ByteBitVector> getRemainingVectors() {
        return remainingVectors;
    }

    @Override
    public void setLastUpdated(Instant lastUpdated) {
        bbv.writeInstant(FieldDefs.CORE_LAST_UPDATED, lastUpdated);
    }

    @Override
    public void setPurposesConsent(IntIterable purposesConsent) {
        writeFromBitset((BitSetIntIterable) purposesConsent, FieldDefs.CORE_PURPOSES_CONSENT);
    }

    @Override
    public void setVendorConsent(IntIterable vendorConsent) {
        writeVendors((BitSetIntIterable) vendorConsent, bbv, FieldDefs.CORE_VENDOR_MAX_VENDOR_ID, FieldDefs.CORE_VENDOR_BITRANGE_FIELD);
    }

    private void writeVendors(BitSetIntIterable bitSetIntIterable, ByteBitVector bbv, FieldDefs coreVendorMaxVendorId, FieldDefs coreVendorBitrangeField) {
        // TODO
    }
}
