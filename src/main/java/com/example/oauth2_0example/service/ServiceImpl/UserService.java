package com.example.oauth2_0example.service.ServiceImpl;

import com.example.oauth2_0example.model.dto.UserDTO;
import com.example.oauth2_0example.model.vo.UserVO;
import com.example.oauth2_0example.service.Mapper.ExtensionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements ExtensionMapper, UserDetailsService {

    @Autowired
    ExtensionMapper extensionMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String user_id = username;
        try {
            return extensionMapper.findByUserId(user_id)
                    .map(user -> addAuthorities(user))
                    .orElseThrow(() -> new UsernameNotFoundException(user_id + "> 찾을 수 없습니다."));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UserVO addAuthorities(UserVO user) {
        user.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(user.getUser_role())));

        return user;
    }

    @Override
    public Optional<UserVO> findByUserId(String user_id) throws Exception {
        Optional<UserVO> userVO = Optional.empty();
        try {
            userVO = extensionMapper.findByUserId(user_id);
        } catch (NullPointerException e) {
            log.error("DBServiceException",e);
            throw new Exception("저장된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error("DBServiceException",e);
            throw new Exception("데이터 저장에 실패했습니다.");
        }
        return userVO;
    }

    @Override
    public void SignUp(UserDTO userDTO) throws Exception {
        try {
            extensionMapper.SignUp(userDTO);
        } catch (NullPointerException e) {
            log.error("DBServiceException",e);
            throw new Exception("저장된 데이터가 없습니다.");
        } catch (Exception e) {
            log.error("DBServiceException",e);
            throw new Exception("데이터 저장에 실패했습니다.");
        }
    }
}
