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

import com.iabtcf.decoder.TCString;
import com.iabtcf.utils.IntIterable;

import java.time.Instant;

public interface WritableTCString extends TCString {

    static String encode(TCString tcString) {
        return TCStringEncoder.encode(tcString);
    }

    /**
     * Sets the epoch deciseconds (0.1 of a second) when TC String was last updated
     *
     * @since 1.0
     * @param lastUpdated  timestamp record was last updated
     */
    void setLastUpdated(Instant lastUpdated);

    /**
     * Sets the Consent Management Platform ID that last updated the TC String
     *
     * @since 1.0
     * @param cmpId  the Consent Management Platform ID
     */
//    void setCmpId(int cmpId);

    /**
     * Sets the Consent Management Platform version of the CMP that last updated this TC String
     *
     * @since 1.0
     * @param cmpVersion version of the Consent Management Platform that updated this record
     */
//    void setCmpVersion(int cmpVersion);

    /**
     *
     * Sets the CMP Screen number at which consent was given for a user with the CMP that last updated this
     * TC String.
     *
     * The number is a CMP internal designation and is CmpVersion specific. The number is used for
     * identifying on which screen a user gave consent as a record.
     *
     * @since 1.0
     * @param consentScreen  the screen number identifier
     */
//    void setConsentScreen(int consentScreen);

    /**
     * Sets the two-letter ISO 639-1 language code in which the CMP UI was presented.
     *
     * @since 1.0
     * @param consentLanguage the language string
     */
//    void setConsentLanguage(String consentLanguage);

    /**
     * Sets the user’s consent value for each Purpose established on the legal basis of consent. The
     * Purposes are numerically identified and published in the Global Vendor List.
     *
     * An alias for PurposesAllowed
     *
     * @since 1.0
     * @param purposesConsent  The integer values for each established Purpose.
     */
    void setPurposesConsent(IntIterable purposesConsent);

    /**
     * Sets the vendor identifiers that have consent to process this users personal data. The vendor
     * identifiers are published in the GVL.
     *
     * @since 1.0
     * @param vendorConsent the vendor identifiers.
     */
    void setVendorConsent(IntIterable vendorConsent);
}
