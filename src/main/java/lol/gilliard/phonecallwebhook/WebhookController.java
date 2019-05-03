package lol.gilliard.phonecallwebhook;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Say;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class WebhookController {

    private AtomicInteger callerNumber = new AtomicInteger();

    @GetMapping(path="/call", produces="application/xml")
    @ResponseBody
    public String respondToPhoneCall() {

        VoiceResponse.Builder voiceBuilder = new VoiceResponse.Builder();

        Say greeting = new Say.Builder("Hello caller number " + callerNumber.incrementAndGet()).build();
        Play music = new Play.Builder("https://static.gilliard.lol/Might_As_Well_Be_Spring.mp3").build();

        return voiceBuilder.say(greeting).play(music).build().toXml();
    }

}
