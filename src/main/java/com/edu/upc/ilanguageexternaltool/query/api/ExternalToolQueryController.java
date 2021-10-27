package com.edu.upc.ilanguageexternaltool.query.api;

import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolHistoryView;
import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolHistoryViewRepository;
import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolView;
import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/externaltool")
@Api(tags = "ExternalTool")
public class ExternalToolQueryController {
    private final ExternalToolViewRepository externalToolViewRepositoy;
    private final ExternalToolHistoryViewRepository externalToolHistoryViewRepository;

    public ExternalToolQueryController(ExternalToolViewRepository externalToolViewRepositoy, ExternalToolHistoryViewRepository externalToolHistoryViewRepository) {
        this.externalToolViewRepositoy = externalToolViewRepositoy;
        this.externalToolHistoryViewRepository = externalToolHistoryViewRepository;
    }

    @Operation(summary="Get all subscriptions", description="This endpoind returns all the availeble subscription for Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("")
    public ResponseEntity<List<ExternalToolView>> getAll(){
        try {
            return new ResponseEntity<List<ExternalToolView>>(externalToolViewRepositoy.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary="Get subscription by id", description="This endpoind returns an specific subscription by the given ID Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping(value= "id/{id}", method = RequestMethod.GET)
    public ResponseEntity<ExternalToolView> getById(@PathVariable(name="id") String subscriptionId){
        try {
            return new ResponseEntity<ExternalToolView>(externalToolViewRepositoy.getById(subscriptionId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Get subscription by name", description="This endpoind returns an specific subscription by the given ID Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping( value = "name/{name}",method = RequestMethod.GET)
    public ResponseEntity<ExternalToolView> getByName(@PathVariable(name="name") String externalToolName){
        try {
            return new ResponseEntity<ExternalToolView>(externalToolViewRepositoy.findByName(externalToolName), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary="Get history subscription by id", description="This endpoind returns the list with the history of an specific subscription by the given ID Ilanguage Application", tags = {"subscriptions"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping(value= "historyid/{historyid}", method = RequestMethod.GET)
    public ResponseEntity<List<ExternalToolHistoryView>> getHistoryById(@PathVariable(name="historyid") String externalToolId){
        try {
            return new ResponseEntity<List<ExternalToolHistoryView>>(externalToolHistoryViewRepository.getExternalToolHistoryById(externalToolId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
