package org.learning.mlfun.dt.id3.classification;

import org.learning.mlfun.dt.id3.IClassification;

public enum SpamCheckClassifications implements IClassification {
    KNOWN_SENDER, UNKOWN_SENDER, SPAM, NOT_SPAM;

    @Override
    public boolean isFinal() {
        return this == SPAM || this == NOT_SPAM;
    }


}
