/*
 * author: Filip Vozarevic
 */
package com.example.demo.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
public class Korisnik implements UserDetails {
	
	private static final long serialVersionUID = 8158909379450641326L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "grad")
	private String grad;
	
	@Column(name = "drzava")
	private String drzava;
	
	@Column(name = "jmbg", nullable = false)
	private Long jmbg;
	
	@Column(name = "adresa")
	private String adresa;
	
	@Column(name = "telefon")
	private String telefon;
	
	@Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;
	
	/*
	 * @Column(name = "datumRodjenja", nullable = true) private Date datumRodjenja;
	 */
		
	
	@OneToMany(mappedBy = "korisnik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Odsustvo> odsustva = new HashSet<Odsustvo>();
	
	@Column(name = "enabled")
    private boolean enabled;
	
	@Column(name = "locked")
    private boolean locked;
	
	@Column(name = "expired")
    private boolean expired;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "uloga_korisnik",
	            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "uloga_id", referencedColumnName = "id"))
    private List<Uloga> uloge;
	
	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String ime, String prezime, String email, String password, String grad, String drzava, Long jmbg,
			String adresa,String telefon) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.grad = grad;
		this.drzava = drzava;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.telefon = telefon;
		this.enabled = true;
		this.locked = false;
		this.expired = false;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	 public String getPassword() {
	        return password;
	 }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(DateTime.now().getMillis());
        this.setLastPasswordResetDate( now );
        this.password = password;
    }

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public Long getJmbg() {
		return jmbg;
	}

	public void setJmbg(Long jmbg) {
		this.jmbg = jmbg;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Odsustvo> getOdsustva() {
		return odsustva;
	}

	public void setOdsustva(Set<Odsustvo> odsustva) {
		this.odsustva = odsustva;
	}
	
	public void setUloge(List<Uloga> uloge) {
        this.uloge = uloge;
    }
	
	public List<Uloga> getUloge() {
		return uloge;
	}
	
	public String getUlogeString() {
		String rez = "";
		for(Uloga u:uloge)
		{
			rez += u.name;
			rez += " ";
		}
		return rez;
	}


	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", password="
				+ password + ", grad=" + grad + ", drzava=" + drzava + ", jmbg=" + jmbg + ", adresa=" + adresa
				+ ", uloge=" + getUlogeString()  + ", odsustva=" + odsustva + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.uloge;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
	
	
}
