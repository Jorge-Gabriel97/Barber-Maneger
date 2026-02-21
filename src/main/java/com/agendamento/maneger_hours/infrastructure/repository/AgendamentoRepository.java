package com.agendamento.maneger_hours.infrastructure.repository;

import com.agendamento.maneger_hours.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
    Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal);

    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

    // O nome deve ser findBy + NomeDoAtributo + Between
    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime inicio, LocalDateTime fim);

    Agendamento findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);
}
