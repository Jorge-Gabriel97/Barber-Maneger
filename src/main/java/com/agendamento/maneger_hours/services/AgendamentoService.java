package com.agendamento.maneger_hours.services;

import com.agendamento.maneger_hours.infrastructure.entity.Agendamento;
import com.agendamento.maneger_hours.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(
                agendamento.getServico(), horaAgendamento, horaFim
        );

        if (Objects.nonNull(agendados)) {
            throw new RuntimeException("Horário já reservado, selecione outro!");
        }

        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {

    }
}