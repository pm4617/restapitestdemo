   JSONObject call(URI uri, JSONObject r) {
        LOG.debug("In call uri={}, req={} ", uri.toString(), r.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(r.toString(), headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            return new JSONObject(response.getBody());
        } catch (HttpStatusCodeException e) {
            LOG.warn("Got HTTP Exception: {}", e.getMessage());
            if (HttpStatus.OK != e.getStatusCode()) {
                LOG.warn("HTTP status code is not the expected({}) one. Acutal({})", HttpStatus.OK, e.getStatusCode());
            }
            LOG.debug("Got stack trace: ", e);
            LOG.info(e.getResponseBodyAsString());
            return new JSONObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
