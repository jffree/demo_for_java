package com.jffree.java_demo.protocol.protobuf;


import com.google.protobuf.InvalidProtocolBufferException;
import com.jffree.java_demo.protocol.protobuf.PersonProbuf.Person;
import com.jffree.java_demo.protocol.protobuf.PersonProbuf.Person.PhoneNumber;

import java.util.List;

public class TestPersonProbuf {
    public static void main(String[] args)
    {
        PersonProbuf.Person.Builder builder = PersonProbuf.Person.newBuilder();
        builder.setEmail("JohnDoe@email.com");
        builder.setId(1);
        builder.setName("John Doe");
        builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("1001").setType(PersonProbuf.Person.PhoneType.MOBILE));
        builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder().setNumber("1002").setType(PersonProbuf.Person.PhoneType.HOME));

        Person person = builder.build();
        byte[] buf = person.toByteArray();

        try
        {
            Person person2 = PersonProbuf.Person.parseFrom(buf);

            System.out.println(person2.getName() + ", " + person2.getEmail());

            List<PhoneNumber> lstPhones = person2.getPhoneList();

            for (PhoneNumber phoneNumber : lstPhones)
            {
                System.out.println(phoneNumber.getNumber());
            }
        }
        catch (InvalidProtocolBufferException e)
        {
            e.printStackTrace();
        }

        System.out.println(buf);
    }
}
