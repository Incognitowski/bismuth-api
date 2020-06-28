package com.bismuth.bismuth.framework.data

import com.bismuth.bismuth.user.User
import javax.persistence.JoinColumn
import javax.persistence.OneToOne


open class OwnableModel : BaseModel() {

    @OneToOne
    @JoinColumn(
            name = "created_by",
            nullable = false,
            referencedColumnName = "user_id"
    )
    var createdBy: User? = null;

    @OneToOne
    @JoinColumn(
            name = "owned_by",
            nullable = false,
            referencedColumnName = "user_id"
    )
    var ownedBy: User? = null;


}