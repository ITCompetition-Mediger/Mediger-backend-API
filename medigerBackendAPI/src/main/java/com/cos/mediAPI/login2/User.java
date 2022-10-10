//package com.cos.mediAPI.login2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//import org.hibernate.annotations.ColumnDefault;
//
//import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplus;
//
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@NoArgsConstructor
//@Entity
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column
//    private String picture;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//    
//    @Column(name = "provider_type")
//    @Enumerated(EnumType.STRING)
//    private ProviderType providerType;
//    
//    @Builder
//    public User(String name, String email, String picture, Role role) {
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//        this.role = role;
//    }
//
//    public User update(String name, String picture) {
//        this.name = name;
//        this.picture = picture;
//
//        return this;
//    }
//
//    public String getRoleKey() {
//        return this.role.getKey();
//    }
//    @OneToMany(mappedBy="user")
//    private List<medigerplus> medigerPluses = new ArrayList<>();
//}
