package ru.vse.zoo.impl.clinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vse.zoo.Observable;
import ru.vse.zoo.UI;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ZooClinicTest {
    @Mock
    private UI ui;
    @InjectMocks
    private ZooClinic zooClinic;

    @Test
    public void should_observable_be_unhealthy() {
        var observable = mock(Observable.class);
        var startTime = Instant.now();
        when(ui.readYesOrNo(eq("Is animal healthy"))).thenReturn(false);

        var res = zooClinic.examine(observable);
        var stopTime = Instant.now();
        assertFalse(res);
        verify(observable).setHealthy(eq(false));
        ArgumentCaptor<Instant> surveyTimeCptr = ArgumentCaptor.forClass(Instant.class);
        verify(observable).setSurveyDate(surveyTimeCptr.capture());
        var surveyTime = surveyTimeCptr.getValue();
        assertFalse(surveyTime.isBefore(startTime));
        assertFalse(surveyTime.isAfter(stopTime));
    }

    @Test
    public void should_observable_be_healthy() {
        var observable = mock(Observable.class);
        var startTime = Instant.now();
        when(ui.readYesOrNo(eq("Is animal healthy"))).thenReturn(true);

        var res = zooClinic.examine(observable);
        var stopTime = Instant.now();
        assertTrue(res);
        verify(observable).setHealthy(eq(true));
        ArgumentCaptor<Instant> surveyTimeCptr = ArgumentCaptor.forClass(Instant.class);
        verify(observable).setSurveyDate(surveyTimeCptr.capture());
        var surveyTime = surveyTimeCptr.getValue();
        assertFalse(surveyTime.isBefore(startTime));
        assertFalse(surveyTime.isAfter(stopTime));
    }
}
