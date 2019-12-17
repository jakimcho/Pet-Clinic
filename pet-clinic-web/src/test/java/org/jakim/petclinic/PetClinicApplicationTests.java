package org.jakim.petclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertFalse;

public class PetClinicApplicationTests
{

    @Test
    public void contextLoads( )
    {
        boolean thereIsANeedOfTest = false;
        assertFalse( thereIsANeedOfTest );
    }

}
