package com.fsse2305.fsse2305_project_backend.api;

import com.fsse2305.fsse2305_project_backend.config.EnvConfig;
import com.fsse2305.fsse2305_project_backend.data.transaction.dto.response.TransactionProcessingResponseDto;
import com.fsse2305.fsse2305_project_backend.data.transaction.dto.response.TransactionResponseDto;
import com.fsse2305.fsse2305_project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2305.fsse2305_project_backend.service.TransactionService;
import com.fsse2305.fsse2305_project_backend.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RequestMapping("/transaction")
@RestController
public class TransactionApi {
    private final TransactionService transactionService;

    @Autowired
    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResponseDto postTransaction(JwtAuthenticationToken jwtToken){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto(transactionService.postTransaction(firebaseUserData));
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionById(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto(transactionService.getTransaction(firebaseUserData, tid));
    }

    @PatchMapping("/{tid}/pay")
    public TransactionProcessingResponseDto patchTransactionStatusProcessing(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionProcessingResponseDto(transactionService.patchTransaction(firebaseUserData, tid));
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto patchTransactionStatusSuccess (JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionResponseDto((transactionService.patchTransactionSuccess(firebaseUserData, tid)));
    }


}
