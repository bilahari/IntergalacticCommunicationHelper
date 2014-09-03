package ich;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ich.core.PlanetConversationProcessor;

public class App 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"Spring-Config.xml"});

    	PlanetConversationProcessor pcp=new PlanetConversationProcessor();
    	pcp.startRecording();
    	pcp.processConversation();
    	pcp.printAnswers();
    }
}