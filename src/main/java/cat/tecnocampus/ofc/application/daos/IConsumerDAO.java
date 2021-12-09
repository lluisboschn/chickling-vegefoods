// SPRINT 5

package cat.tecnocampus.ofc.application.daos;

import cat.tecnocampus.ofc.application.dtos.ConsumerDTO;

public interface IConsumerDAO {
    public ConsumerDTO getConsumer(String mail);
    public ConsumerDTO getConsumerByEmail(String email);
}
