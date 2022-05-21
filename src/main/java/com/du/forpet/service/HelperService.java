package com.du.forpet.service;

import com.du.forpet.domain.dto.*;
import com.du.forpet.domain.entity.Helper;
import com.du.forpet.repository.HelperRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HelperService {

    private Logger logger = LoggerFactory.getLogger(HelperService.class);

    private final HelperRepository helperRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(HelperSaveRequestDto helperSaveRequestDto) {

        boolean isExist = helperRepository.existsByLoginId(helperSaveRequestDto.getLoginId());

        try {
            if(isExist)
                throw new Exception("이미 존재하는 아이디 입니다. id: " + helperSaveRequestDto.getLoginId());
        }catch (Exception e) {
            logger.error(e.getMessage());
            return 0L;
        }
        String encodedPassword = passwordEncoder.encode(helperSaveRequestDto.getPassword());

        return helperRepository.save(helperSaveRequestDto.toEntity(encodedPassword)).getId();
    }

//    public HelperResponseDto findById(Long id) {
//        Helper helper = findByIdOrElseThrowException(id);
//        return new HelperResponseDto(helper);
//    }
//
//    @Transactional
//    public Long update(Long id, HelperUpdateRequestDto requestDto) {
//        Helper helper = findByIdOrElseThrowException(id);
//
//        helper.update(requestDto.getName(),
//                        requestDto.getPhoneNumber(),
//                        requestDto.getDistrict());
//
//        return id;
//    }
//
//    @Transactional
//    public void withdraw(Long id) {
//        Helper helper = findByIdOrElseThrowException(id);
//        helper.withdraw(id);
//    }
//
//    @Transactional
//    public List<ReservationListResponseDto> findReservationsById(Long id) {
//        Helper helper = findByIdOrElseThrowException(id);
//        List<ReservationListResponseDto> reservationList = helper
//                                                            .getReservations()
//                                                            .stream()
//                                                            .map(ReservationListResponseDto::new)
//                                                            .collect(Collectors.toList());
//        return reservationList;
//    }
//
    private Helper findByIdOrElseThrowException(Long id) {
        return helperRepository
                    .findById(id)
                    .orElseThrow(
                            ()-> new IllegalArgumentException("해당 아이디의 헬퍼가 존재하지 않습니다. id: " + id));
    }
}
