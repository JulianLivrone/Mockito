package org.example.test_doubles.dummy;

import org.example.test_doubles.dummy.EmailService;

public class DummyEmailService implements EmailService {

    @Override
    public void sendEmail(String message) {
        throw new AssertionError("Method not implemented");
    }
}
