
<div class="modal fade" id="newCate" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header"></div>
			<div class="modal-body">
				<div class="row">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-header">New</div>
							<div class="card-body">
								<div class="card-title">
									<h3 class="text-center title-2">Category</h3>
								</div>
								<hr>							
									<div class="form-group">
										<label for="cc-payment" class="control-label mb-1">Title</label>
										<input id="cateName" name="name" type="text"
											class="form-control" aria-required="true" data-val="true"
											data-val-required="Please enter the name"
											aria-invalid="false" placeholder="name">
									</div>
									<div class="form-group has-success">
										<label for="cc-name" class="control-label mb-1">Description</label>
										<input id="summernote" name="description" type="text"
											class="form-control cc-name valid" autocomplete="cc-name"
											aria-required="true" aria-invalid="false"
											aria-describedby="cc-name-error"> <span
											class="help-block field-validation-valid"
											data-valmsg-for="cc-name" data-valmsg-replace="true"></span>
									</div>
									<div>
										<button onclick="addNewCate();" type="submit"
											class="btn btn-lg btn-info btn-block">
											<span id="payment-button-amount">Submit</span> <span
												id="payment-button-sending" style="display: none;">Sending…</span>
										</button>
									</div>								
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>
