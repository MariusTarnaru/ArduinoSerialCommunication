import java.io.IOException;
import java.util.Arrays;

import com.fazecast.jSerialComm.SerialPort;

/**
 * Simple application that is part of an tutorial.
 * The tutorial shows how to establish a serial connection between a Java and Arduino program.
 * @author Michael Schoeffler (www.mschoeffler.de)
 *
 */
public class SerialCommunicationArduino {

    public static void main(String[] args) throws IOException, InterruptedException {
        SerialPort sp = SerialPort.getCommPort("/COM7"); // device name TODO: must be changed
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written

        if (sp.openPort()) {
            System.out.println("Port is open :)");
            Thread.sleep(3400);
        } else {
            System.out.println("Failed to open port :(");
            return;
        }

        for (Integer i = 0; i < 5; ++i) {
            sp.getOutputStream().write(i.byteValue());
            sp.getOutputStream().flush();
            System.out.println("Sent number: " + i);
            Thread.sleep(700);
        }

        byte[] myvar = "Any String you want".getBytes();
        sp.getOutputStream().write(myvar);
        sp.getOutputStream().flush();
        System.out.println("My String myvar is: "+ Arrays.toString(myvar));

        if (sp.closePort()) {
            System.out.println("Port is closed :)");
        } else {
            System.out.println("Failed to close port :(");
        }


    }

}
