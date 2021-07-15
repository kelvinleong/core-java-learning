package protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.util.JsonFormat;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import protobuf.messages.HomeAddress;
import protobuf.messages.Student;


public class ProtobufTest {
    @SneakyThrows
    private void printAbstractMessage(AbstractMessage message) {
        String r = JsonFormat.printer().includingDefaultValueFields().print(message);
        System.out.println(r);
    }

    @Test
    public void testProtobufWriter() {
        var student = Student.newBuilder()
                .setId("1")
                .setPreset("preset=at_market")
                .setAddress(HomeAddress.newBuilder()
                        .setAddress("HONG KONG")
                        .setPostCode("code=at_market")
                        .build())
                .build();

        printAbstractMessage(student);
    }
}
