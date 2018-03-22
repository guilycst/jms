#DLQ
- Stands for "dead letter queue"
- All messages unable to be delivered are stored in the DLQ
- ActiveMQ by default tries 6 redelivers before sending a message to the DLQ
