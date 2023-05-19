package org.seven.claim;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.activation.DataHandler;
import javax.mail.internet.MimeUtility;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.springframework.stereotype.Component;
@Component
public class AttachmentProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		AttachmentMessage attMsg = exchange.getIn(AttachmentMessage.class);
		String encodedSubject = (String) attMsg.getHeader("Subject");
		String subject = "";
	    if (encodedSubject != null) {
	    	try {
	    		subject =  MimeUtility.decodeText(MimeUtility.unfold(encodedSubject));
	        } catch (UnsupportedEncodingException ex) {
	        	throw new Exception(ex);
	        }
	    }
		Map<String, DataHandler> attachments = attMsg.getAttachments();
	    if (attachments.size() > 0) {   
		     for (String name : attachments.keySet()) {   
		        
		     }   
	    } 
	}

}
