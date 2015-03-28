package lt.pap.model;

import java.io.Serializable;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(schema = "pap", name = "WPart")
public class WPart implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 3544256746775808177L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelengine_id")
    private ModelEngine modelengine;
    
    @Column(name = "year", nullable = false)
    private Year year;
    
    @Column(name = "fullcode", nullable = false, length = 40)
    private String fullcode;
    
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    
}
