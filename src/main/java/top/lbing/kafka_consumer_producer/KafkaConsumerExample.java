package top.lbing.kafka_consumer_producer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaConsumerExample {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "test");// 用户组
		props.put("enable.auto.commit", "true");// 自动提交消费者记录处理到什么位置（offset）了
		props.put("auto.commit.interval.ms", "1000");// 每隔1000ms消费者提交offset到kafka
		props.put("session.timeout.ms", "30000");// group coordinator检测consumer发生崩溃所需的时间
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");// 序列化器
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("topic1"));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
		}
		// consumer.close();
	}
}
