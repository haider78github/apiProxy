package com.softech.ls360.lms.proxy.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

import com.softech.ls360.lms.proxy.entities.VU360User;

/**
 * Spring Data, a Spring project separate from but dependent on Spring Framework, can write your repositories for you. All you have 
 * to do is create an interface and Spring Data dynamically generates the necessary code to implement that interface at run time.
 * 
 * When you use Spring Data, that repository code gets written for you. There are very few cases when you would ever need to write 
 * repository code that Spring Data couldn’t handle automatically, and facilities exist to handle those rare occasions.
 * 
 * Spring Data supports a variety of data access methodologies, including JPA, JdbcTemplate, NoSQL, and more. Its primary subproject,
 * Spring Data Commons, provides a core toolset that all other subprojects use to create repositories. The Spring Data JPA subproject
 * provides support for repositories implemented against the Java Persistence API.
 * 
 * One of the tools supplied by Spring Data Commons is the org.springframework.data.repository.Repository<T, ID extends Serializable>
 * interface. All Spring Data repository interfaces must extend this marker interface, which specifies no methods. Only interfaces 
 * extending Repository are eligible for dynamic implementation. The generic type parameters T and ID capture the entity type and 
 * identifier type, respectively.
 * 
 * You may create an interface that extends Repository directly, but because it specifies no methods, you will probably never do this.
 * A more useful approach is to extend org.springframework.data.repository.CrudRepository<T, ID>, which specifies numerous methods 
 * for basic CRUD operations.
 * 
 * 	-- count() returns a long representing the total number of unfiltered entities extending T.
 	-- delete(T) and delete(ID) delete the single, specified entity, whereas delete(Iterable<? extends T>) deletes multiple entities
 	   and deleteAll() deletes every entity of that type.
 
 	-- exists(ID) returns a boolean indicating whether the entity of this type with the given surrogate key exists.
 	-- findAll() returns all entities of type T, whereas findAll(Iterable<ID>) returns the entities of type T with the given 
 	   surrogate keys. Both return Iterable<T>.
 
 	-- findOne(ID) retrieves a single entity of type T given its surrogate key.
 	-- save(S) saves the given entity (insert or update) of type S where S extends T, and returns S, the saved entity.
 	-- save(Iterable<S>) saves all the entities (again, S extends T) and returns the saved entities as a new Iterable<S>.
 * 
 * All Spring Data projects already know how to implement all these methods for a given type. You’ll notice, however, that this 
 * repository still doesn’t specify methods that support paging and sorting. This is so that these methods don’t clutter any 
 * repositories that you don’t want to support paging and sorting. If you want a repository to provide paging and sorting methods, 
 * its interface can extend org.springframework.data.repository.PagingAndSortingRepository<T, ID extends Serializable>.

	-- findAll(Sort) returns all T entities as an Iterable<T> sorted with the provided Sort instructions.
	-- findAll(Pageable) returns a single org.springframework.data.domain.Page<T> of entities sorted and bounded with the provided 
	   Pageable instructions.
 * 
 * An org.springframework.data.domain.Sort object encapsulates information about the properties that should be used to sort a result
 * set and in what direction they should be sorted. An org.springframework.data.domain.Pageable encapsulates a Sort as well as the 
 * number of entities per page and which page to return (both ints). In a web application, you don’t usually have to worry about 
 * creating Pageable objects on your own. Spring Data provides two 
 * org.springframework.web.method.support.HandlerMethodArgumentResolver implementations that can turn HTTP request parameters into 
 * Pageable and Sort objects, respectively: org.springframework.data.web.PageableHandlerMethodArgumentResolver and 
 * org.springframework.data.web.SortHandlerMethodArgumentResolver.
 * 
 * All these predefined methods are helpful, and the standardized Sort and Pageable objects definitely come in handy, but you still 
 * have no way to find specific entities or lists of entities using anything other than surrogate keys — at least, not without 
 * creating your own method implementations. This is where Spring Data’s query methods come in to play.
 * 
 * The TicketRepository interfaces don’t define any methods because they don’t need to. The methods are all defined in the 
 * CrudRepository interface, and the type variable values make the method argument types and return types concrete.
 * 
 * You probably noticed that the JPA repository interfaces you created extended CrudRepository from Spring Data Commons instead of 
 * JpaRepository from Spring Data JPA. You should only extend the interface whose methods you want to expose to your services. In 
 * most cases you shouldn’t need to expose the fact that your repositories use JPA, and so you shouldn’t extend JpaRepository. If you
 * want to expose pagination capabilities, extend PagingAndSortingRepository. Only if you need to expose JPA-specific behavior, such
 * as batch deletions or EntityManager flushing, should you extend JpaRepository.
 * 
 * The query method language is really quite powerful. There’s not a whole lot you can’t do with Spring Data interface methods. In 
 * almost all cases, you can simply create an interface, sit back, and let Spring Data do the work for you. Every once in a while, 
 * however, you’ll come across a situation that Spring Data can’t handle. One example of this is performing full-text searching.
 * 
 * Another example is user-generated dynamic queries. Usually you want to strictly structure the queries a user can perform on 
 * persisted data. Allowing a user to filter on virtually any property can have disastrous performance implications. But where you 
 * understand the risks and plan accordingly, such a tool can be extremely powerful and useful for your users. It is also not 
 * possible to achieve this using standard Spring Data query methods.
 * 
 * Although Spring Data does not provide a standard mechanism for performing dynamic queries, Spring Data JPA does provide two 
 * proprietary mechanisms for doing so using the JPA criteria API or Query's predicates. If you use JPA repositories, you can use one
 * of these mechanisms. If you use some other Spring Data repository type, you may still have to create your own mechanism for 
 * dynamic queries.
 * 
 * The first step in customizing an individual repository is to create an interface for that customization. This interface should be 
 * separate from the actual repository interface and should specify all methods (at least one) that have custom implementations for 
 * your repository. The repository interface should then extend the customization interface(VU360UserRepositoryCustom).
 * 
 * You can name the repository and customization interfaces whatever you want. When Spring Data finds VU360UserRepository, it first looks
 * for a class in the same package named VU360UserServiceImpl (or whatever you named the interface plus Impl) and instantiates and 
 * wires that class as any ordinary Spring bean. This class should implement the VU360UserRepositoryCustom interface and provide the 
 * behavior for its methods. Spring Data delegates to this implementation when the customized methods are invoked on the 
 * VU360UserRepository. For all other methods, Spring Data provides the standard Spring Data implementations.
 * 
 * First, create an interface to reflect what you want the VU360UserRepositoryCustom to do. Your VU360UserRepository repository interface 
 * should extend this interface.
 * 
 * You added VU360UserRepositoryCustom to the VU360UserRepository interface, but Spring Data still doesn’t know how to find the 
 * search method implementation. To solve this, you need a VU360UserServiceImpl class. Notice that this class implements only 
 * VU360UserRepositoryCustom. It does not implement VU360UserRepository because Spring Data JPA does that for you.
 * 
 * @author basit.ahmed
 *
 */
public interface VU360UserRepository extends CrudRepository<VU360User, Long>{

	VU360User findByUserGuid(String userGuid);
	VU360User findByUsername(String userName);
	
	@EntityGraph(value = "VU360USER.UserAndRole", type = EntityGraphType.LOAD)
	VU360User findUserAndRolesByUsername(String userName);
	
}
