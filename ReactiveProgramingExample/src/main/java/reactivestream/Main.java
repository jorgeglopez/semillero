package reactivestream;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        SubmissionPublisher<Integer> submissionPublisher = new SubmissionPublisher<>();
        //Procesador de la información
        Flow.Processor<Integer,Integer> powProcessor = new PowProcessor();
        // Gestionador de la información.
        Flow.Subscriber<Integer> printSubscriber = new PrintSubscriber();

        submissionPublisher.subscribe(powProcessor);
        powProcessor.subscribe(printSubscriber);
        IntStream.range(0,20).forEach((i)->{
            submissionPublisher.submit(i);
            Sleeper.sleep(2000);
        });
        submissionPublisher.close();
    }
}