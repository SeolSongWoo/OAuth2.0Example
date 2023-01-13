package com.example.oauth2_0example.service.Mapper;


import com.example.oauth2_0example.model.dto.UserDTO;
import com.example.oauth2_0example.model.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExtensionMapper {
    Optional<UserVO> findByUserId(String user_id)  throws Exception;

    void SignUp(UserDTO userDTO) throws Exception;
}
