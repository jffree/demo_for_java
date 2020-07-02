package com.jffree.java_demo.protocol.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFastJson {

    private List<Person>        personList = new ArrayList<>();
    private Map<String, Person> personMap  = new HashMap<>();
    private Person              person;

    @Before
    public void setup() {
        personList.add(new Person(15, "xiaoming", LocalDateTime.now().withNano(0)));
        personList.add(new Person(20, "xiaohong", LocalDateTime.now().withNano(0)));
        personList.forEach(p ->{
            personMap.put(p.getName(), p);
        });
        person = new Person(10, "xiaotian", LocalDateTime.now().withNano(0));
    }

    @Test
    public void objectConvertToString() {
        System.out.println(JSON.toJSONString(personList, SerializerFeature.PrettyFormat));
    }

    @Test
    public void stringConvertToObject() {
        Person p = JSON.parseObject(JSON.toJSONString(person), Person.class);
        Assert.assertEquals(person, p);
    }

    @Test
    public void objectConvertToBytes() {
        byte[] jsonBytes = JSON.toJSONBytes(personList);
    }

    @Test
    public void bytesConvertToObject() {
        Person p = JSON.parseObject(JSON.toJSONBytes(person), Person.class);
        Assert.assertEquals(p, person);
    }

    @Test
    public void customizeContextValueFilter() {
        ContextValueFilter valueFilter = new ContextValueFilter() {
            @Override
            public Object process(BeanContext beanContext, Object o, String name, Object value) {
                if (name.equals("FULL NAME")) {
                    return ((String) value).toUpperCase();
                }
                return value;
            }
        };
        System.out.println(JSON.toJSONString(personList, valueFilter));
    }

    @Test
    public void customizeContextNameFilter() {
        NameFilter nameFilter = new NameFilter() {
            @Override
            public String process(Object o, String name, Object value) {
                return name.toLowerCase().replace(' ', '_');
            }
        };
        //SerializeConfig.getGlobalInstance().addFilter(Person.class, nameFilter);
        System.out.println(JSON.toJSONString(personList, nameFilter));
    }

    @Test
    public void customizeObjectSerializer() {
        ObjectSerializer serializer = new ObjectSerializer() {
            @Override
            public void write(JSONSerializer jsonSerializer, Object o, Object fieldName, Type fieldType, int features)
                                                                                                                      throws IOException {
                SerializeWriter out = jsonSerializer.out;
                out.writeString(o.toString());
            }
        };

        ObjectDeserializer deserializer = new ObjectDeserializer() {
            @Override
            public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
                return null;
            }

            @Override
            public int getFastMatchToken() {
                return 0;
            }
        };
        SerializeConfig.getGlobalInstance().put(Person.class, serializer);
        System.out.println(JSON.toJSONString(personMap));
        SerializeConfig.getGlobalInstance().clearSerializers();
    }

}
