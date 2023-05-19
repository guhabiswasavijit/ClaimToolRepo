package org.seven.claim;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mail.SplitAttachmentsExpression;
import org.apache.camel.http.common.HttpMessage;
import org.apache.camel.impl.converter.CoreTypeConverterRegistry;
import org.seven.FileDataProcessor;
import org.seven.MutipartDataConverter;
import org.seven.SalesFile;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailProcessingRoute extends RouteBuilder {
	@Autowired 
	private MutipartDataConverter mutipartDataConverter;
	@Autowired 
	private FileDataProcessor fileProcessor;
    @Override
    public void configure() {
		
    	from("pop3://james@mymailserver.com?password=secret&consumer.delay=1000")  
        .setHeader("subject", constant(subject))  
        .split(new SplitAttachmentsExpression())  
        .process(new MyMailProcessor())  
        .choice()  
          .when(header("attachmentName").isEqualTo("message1.xml"))  
            .to("mock:destination1")  
          .otherwise()  
            .to("mock:destination2")  
        .end();  
          
    }

}
