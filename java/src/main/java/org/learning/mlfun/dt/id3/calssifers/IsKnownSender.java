package org.learning.mlfun.dt.id3.calssifers;

import java.util.Set;

import org.learning.mlfun.dt.id3.EmailBody;
import org.learning.mlfun.dt.id3.IClassification;
import org.learning.mlfun.dt.id3.IClassifier;
import org.learning.mlfun.dt.id3.classification.SpamCheckClassifications;

public class IsKnownSender implements IClassifier<EmailBody> {

    private final Set<String> contacts;

    public IsKnownSender(Set<String> contacts){
        this.contacts = contacts;
    }

    @Override
    public IClassification classify(EmailBody data) {
        if(contacts.contains(data.from)){
            return SpamCheckClassifications.KNOWN_SENDER;
        }else{
            return SpamCheckClassifications.UNKOWN_SENDER;
        }  
    }

}
