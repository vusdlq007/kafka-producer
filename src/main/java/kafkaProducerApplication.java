import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class kafkaProducerApplication {
    public static void main(String[] args) {
        try {
            // set kafka properties
            Properties configs = new Properties();
            configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");  // kafka cluster,  기본포트(9092)
            configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());// KEY_SERIALIZER
            configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // VALUE_SERIALIZER

            // init KafkaProducer (인스턴스 생성)
            KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

            int idx = 0;
            while (true) {

                // set ProducerRecord
                String topic = "testTopic";           // topic name
                Integer partition = 0;           // partition number (default: Round Robin)
                String key = "key-" + idx;       // key  (default: null)
                String data = "record-" + idx;    // data

                ProducerRecord<String, String> record = new ProducerRecord<>(topic, data);
                ProducerRecord<String, String> record2 = new ProducerRecord<>(topic, key, data);
                ProducerRecord<String, String> record3 = new ProducerRecord<>(topic, partition, key, data);

                // send record
                producer.send(record);

                System.out.println("producer.send() >> [topic:" + topic + "][data:" + data + "]");
                Thread.sleep(1000);
                idx++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

