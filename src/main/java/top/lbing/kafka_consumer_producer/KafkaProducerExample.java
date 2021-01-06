package top.lbing.kafka_consumer_producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerExample {
    
    public static void main(String[] args) {
    	Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");// ip:port
        props.put("acks", "all");//传输方式：0发出即成功，1发出Partition Leader接收到消息，数据落盘即成功，all follower同步后即成功。 
        props.put("retries", 0);//数据发送失败重试次数
        props.put("batch.size", 16384);//bacth存放满16384Byte的数据才能发出
        props.put("linger.ms", 1);//每1ms发送一次batch
        props.put("buffer.memory", 33554432);//内存缓冲大小33554432字节
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");// 序列化器，key.serializer，broker希望收到的消息的键和值都是字节数组，必须提供将对象序列化成字节数组的序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");// 序列化器
     
        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++)
          producer.send(new ProducerRecord<>("topic1", "key含"+Integer.toString(i), "value"+Integer.toString(i)));
        producer.close();
    }
}