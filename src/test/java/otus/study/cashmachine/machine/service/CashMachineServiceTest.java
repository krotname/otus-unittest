package otus.study.cashmachine.machine.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import otus.study.cashmachine.TestUtil;
import otus.study.cashmachine.bank.dao.CardsDao;
import otus.study.cashmachine.bank.data.Card;
import otus.study.cashmachine.bank.service.AccountService;
import otus.study.cashmachine.bank.service.impl.CardServiceImpl;
import otus.study.cashmachine.machine.data.CashMachine;
import otus.study.cashmachine.machine.data.MoneyBox;
import otus.study.cashmachine.machine.service.impl.CashMachineServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CashMachineServiceTest {

    @Captor
    private ArgumentCaptor<String> captor;

    @Spy
    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardsDao cardsDao;

    @Mock
    private AccountService accountService;

    @Mock
    private MoneyBoxService moneyBoxService;

    private CashMachineServiceImpl cashMachineService;

    private CashMachine cashMachine = new CashMachine(new MoneyBox());

    @BeforeEach
    void init() {
        cashMachineService = new CashMachineServiceImpl(cardService, accountService, moneyBoxService);
    }


    @Test
    void getMoney() {
        when(cardsDao.getCardByNumber("2222"))
                .thenReturn(new Card(1, "2222", 2L, TestUtil.getHash("1234")));

        List<Integer> money = cashMachineService.getMoney(cashMachine, "2222", "1234", BigDecimal.valueOf(150));

        assertTrue(money.isEmpty());
    }

    @Test
    void putMoney() {
    }

    @Test
    void checkBalance() {

    }

    @Test
    void changePin() {
        when(cardsDao.getCardByNumber(captor.capture()))
                .thenReturn(new Card(1,"1234", 1L, TestUtil.getHash("2222")));

        assertTrue(cashMachineService.changePin("1234", "2222", "1111"));
    }

    @Test
    void changePinWithAnswer() {
        when(cardsDao.getCardByNumber(captor.capture()))
                .thenAnswer(i -> new Card(1, String.valueOf(i.getArguments()[0]), 1L, TestUtil.getHash("2222")));

        assertTrue(cashMachineService.changePin("1234", "2222", "1111"));
    }
}