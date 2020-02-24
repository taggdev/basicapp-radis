package com.simpleradis.service;

import com.simpleradis.SimpleredisAppConfig;
import com.simpleradis.model.Member;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SimpleredisAppConfig.class})
public class TestSimpleredisServiceImpl {

    /**
     * if want declare bean name can set <br><strong>@Qualifier("[bean name]")</strong> <br><br>
     * or not need declare or unknown bean name can comment
     */
    @Autowired
//    @Qualifier("srs")
    public SimpleradisService service;

    @Test
    @Ignore
    public void testSave() {
        assertThat(service, instanceOf(SimpleradisService.class));

        Member member = new Member();
        long currentTM = System.currentTimeMillis();
        member.setId(currentTM);
        member.setFirstName(String.valueOf(currentTM).concat("JTestF"));
        member.setLastName(String.valueOf(currentTM).concat("JTestL"));
        member = service.memberSave(member);
        assertThat(member, notNullValue());
    }

    @Test
//    @Ignore
    public void testGetMemberByIt() {
        assertThat(service, instanceOf(SimpleradisService.class));

        assertThat(service.memberFindById("1528864060947"), notNullValue());
        assertThat(service.memberFindById("1528862904709"), notNullValue());
        assertThat(service.memberFindById("1528863147411"), notNullValue());
        assertThat(service.memberFindById("test"), notNullValue());

    }

    @Test
    @Ignore
    public void testExist() {
        assertThat(service.memberExistsById("1528862904709"), is(true));
        assertThat(service.memberExistsById("1528864060947"), is(true));
        assertThat(service.memberExistsById("1528863147411"), is(true));
//        assertThat(service.memberExistsById("111"), is(true));
    }

    @Test
//    @Ignore
    public void testLoadSave() {

        long startTM = System.currentTimeMillis();

        Member member = new Member();
        long currentTM;
        int maxload = 100000;
        for(int loopi=0 ; loopi<=maxload ; loopi++) {

            currentTM = System.currentTimeMillis();
            member.setId(Long.parseLong(currentTM + "" + loopi));
            member.setFirstName(String.valueOf(currentTM).concat("JTestF"+loopi));
            member.setLastName(String.valueOf(currentTM).concat("JTestL"+loopi));
            member = service.memberSave(member);
//            assertThat(member, notNullValue());
        }

        long endTm = System.currentTimeMillis() - startTM;
        System.out.println("Used time all : "+endTm);
    }
}
