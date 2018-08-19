package com.walis.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.walis.entity.Member;
import com.walis.service.MemberService;

@Component
public class MemberValidator implements Validator {

	@Autowired
	private MemberService memSvc;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member mem = (Member) target;
		System.out.println(mem);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memId", "NotEmpty");
        if (mem.getMemId().length() < 6 || mem.getMemId().length() > 32) {
            errors.rejectValue("memId", "Size.memForm.memId");
        }
        if (memSvc.findByMemId(mem.getMemId()) != null) {
            errors.rejectValue("memId", "Duplicate.memForm.memId");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (mem.getPassword().length() < 8 || mem.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.memForm.password");
        }

        if (!mem.getPasswordConfirm().equals(mem.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.memForm.passwordConfirm");
        }
	}

}
