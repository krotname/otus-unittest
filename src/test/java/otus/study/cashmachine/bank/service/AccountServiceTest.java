package otus.study.cashmachine.bank.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import otus.study.cashmachine.bank.dao.AccountDao;
import otus.study.cashmachine.bank.data.Account;
import otus.study.cashmachine.bank.service.impl.AccountServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountDao accountDao;

    @Spy
    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    void createAccountMock() {
        Account account = accountServiceImpl.createAccount(ArgumentMatchers.any());
        System.out.println(account);
    }

    @Test
    void createAccountCaptor() {
        Account account = accountServiceImpl.createAccount(ArgumentCaptor.forClass(BigDecimal.class).capture());
        assertNull(account);
    }

    @Test
    void addSum() {
    }

    @Test
    void getSum() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void checkBalance() {
    }
}
