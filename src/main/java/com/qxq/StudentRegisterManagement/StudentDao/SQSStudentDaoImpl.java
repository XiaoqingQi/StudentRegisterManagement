package com.qxq.StudentRegisterManagement.StudentDao;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.qxq.StudentRegisterManagement.Entity.Student;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Qualifier("SQS")
public class SQSStudentDaoImpl implements StudentDao{

    AmazonSQS sqs = AmazonSQSClientBuilder.standard()
            .withRegion(Regions.US_WEST_2)
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();


    @Override
    public Collection<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        boolean flag = true;
        while (flag){
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("https://sqs.us-west-2.amazonaws.com/388172715605/MyQueue")
                    .withMaxNumberOfMessages(10);
            List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (Message message : messages){
                JSONObject jsonObject = new JSONObject(message.getBody());
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                int phoneNum = jsonObject.getInt("phoneNum");
                if (id == 0 || name == null || phoneNum == 0){
                    continue;
                } else {
                    Student student = new Student();
                    student.setId(id);
                    student.setName(name);
                    student.setPhoneNum(phoneNum);
                    students.add(student);
                }

                String messageReceiptHandle = message.getReceiptHandle();
                sqs.deleteMessage(new DeleteMessageRequest("https://sqs.us-west-2.amazonaws.com/388172715605/MyQueue", messageReceiptHandle));
            }
            if (messages.size() == 0) {
                flag = false;
                System.out.println("There is no message in queue.");
            }
        }
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public void deleteStudentById(int id) {

    }

    @Override
    public void registerStudent(Student student) {

        JSONObject obj = new JSONObject();
        obj.put("id", "" + student.getId());
        obj.put("name", "" + student.getName());
        obj.put("phoneNum", "" + student.getPhoneNum());

        System.out.println(student.getId() + student.getPhoneNum() + student.getName());

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl("https://sqs.us-west-2.amazonaws.com/388172715605/MyQueue")
                .withMessageBody(obj.toString())
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);

    }

    @Override
    public void updateStudent(Student student) {

    }
}
